package com.abhi.fabkutbusiness.booking.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;

import java.util.ArrayList;
import java.util.List;


public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.MyViewHolder> {

    private int slotSelection = 0;
    private int slotSelected = -1;
    private Activity context;
    private List<String> slotList;
    private ArrayList<String> elapsedSlotList;
    private ArrayList<String> selectedSlotList;
    private int mLayoutResourceId;
    private ArrayList<String> bookedSlots;
    Boolean isEdit;
    private String currentDate;


    public void setSlotSelection(int slotSelection) {
        this.slotSelection = slotSelection;
        notifyDataSetChanged();
    }

    public void setDate(String currentDate) {
        this.currentDate = currentDate;
        notifyDataSetChanged();
    }

    public void setSlotFullSelection(ArrayList<String> _slotList) {
        this.slotSelection = _slotList.size();
        selectedSlotList.clear();
        selectedSlotList.addAll(_slotList);
        int pos = 0;
        for (String slot : slotList) {
            if (slot.equals(_slotList.get(0))) {
                slotSelected = pos;
            }
            pos++;
        }
        notifyDataSetChanged();
    }

    public SlotAdapter(Activity context, int mLayoutResourceId, ArrayList<String> slotList, ArrayList<String> elapsedSlotList, ArrayList<String> selectedSlotList, ArrayList<String> bookedSlotList, Boolean isEdit, String currentDate) {

        this.context = context;
        this.slotList = slotList;
        this.elapsedSlotList = elapsedSlotList;
        this.bookedSlots = bookedSlotList;
        this.mLayoutResourceId = mLayoutResourceId;
        this.isEdit = isEdit;
        this.selectedSlotList = selectedSlotList;
        this.currentDate = currentDate;
    }

    public ArrayList<String> getSelectedSlotList() {
        return selectedSlotList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String slot = slotList.get(position);

        if (elapsedSlotList.contains(currentDate + "/" + slot)) {
            holder.tvSlots.setTextColor(context.getResources().getColor(R.color.colorGrey));
        } else if (bookedSlots.contains(currentDate + "/" + slot)) {
            holder.tvSlots.setTextColor(context.getResources().getColor(R.color.colorRed));
        } else if (selectedSlotList.contains(currentDate + "/" + slot))
            holder.tvSlots.setTextColor(context.getResources().getColor(R.color.colorBlue2));
        else
            holder.tvSlots.setTextColor(context.getResources().getColor(R.color.colorBlack));


        holder.tvSlots.setText(slot);
        holder.tvSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (slotSelection == 0)
                    Utility.showToast(context, "Please select services.");
                else if (bookedSlots.contains(currentDate + "/" + slot)) {
                    Utility.showToast(context, "Slot not free.");
                } else if (elapsedSlotList.contains(currentDate + "/" + slot)) {
                    Utility.showToast(context, "Slot has been elapsed.");
                } else {
                    //   String currDate = Utility.getCurrentDate("dd/MM/yyyy");
                    selectedSlotList = Utility.getSelectedSlotList(currentDate, slot, slotSelection, bookedSlots);
                    if (selectedSlotList == null) {
                        selectedSlotList = new ArrayList<>();
                        Utility.showToast(context, "Slot not free.");
                    } else {
                        if (!Utility.isSeatAvailable(context, selectedSlotList.get(0), selectedSlotList.get(selectedSlotList.size() - 1))) {
                            selectedSlotList = new ArrayList<>();
                            Utility.showToast(context, "Seat not free.");
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return slotList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSlots;


        MyViewHolder(View view) {
            super(view);

            tvSlots = (TextView) view.findViewById(R.id.tv_slots);

        }
    }

    public void setDisabledSlotList(ArrayList<String> bookedSlots) {
        this.bookedSlots.clear();
        this.bookedSlots.addAll(bookedSlots);
        selectedSlotList.clear();
        notifyDataSetChanged();
    }

}