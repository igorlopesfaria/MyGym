package br.com.mygym.exercise.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mygym.exercise.databinding.ExerciseItemListBinding
import br.com.mygym.exercise.model.ExerciseItem
import kotlinx.android.extensions.LayoutContainer

class ExerciseItemAdapter(private val clickListener: ((ExerciseItem) -> Unit)) :
    RecyclerView.Adapter<ExerciseItemAdapter.EventsClosedViewHolder>() {

    private val items = mutableListOf<ExerciseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsClosedViewHolder {
        val itemBinding =
            ExerciseItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsClosedViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: EventsClosedViewHolder, position: Int) {
        val homeEvent: ExerciseItem = items[position]
        viewHolder.bind(homeEvent, clickListener)
    }

    override fun getItemCount() = items.size

    fun setAllItems(list: List<ExerciseItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class EventsClosedViewHolder(private val itemBinding: ExerciseItemListBinding) :
        RecyclerView.ViewHolder(itemBinding.root), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: ExerciseItem, listener: ((ExerciseItem) -> Unit)) {
            itemBinding.nameTXV.text = item.name
            itemBinding.muscleTXV.text = item.muscle
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}