package com.example.saleofcars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saleofcars.databinding.CarItemBinding

class CarAdapter(
    private val onDeleteCarClick: (index: Int) -> Unit,
    ): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val carList = mutableListOf<CarModel>()

    fun updateList(carList: List<CarModel>) {
        this.carList.clear()
        this.carList.addAll(carList)
        notifyDataSetChanged()
    }

    inner class CarViewHolder(
        private val binding: CarItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(carModel: CarModel) {
            carList.indexOf(carModel)
            binding.tvCartitle.text = carModel.model
            binding.tvCaroverView.text = carModel.marka
            binding.flothingButton.setOnClickListener{
                onDeleteCarClick.invoke(carList.indexOf(carModel))
            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CarViewHolder {
        val binding = CarItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.car_item,
                parent,
                false,
            )
        )
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int = carList.size

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int,
    ) {
        holder.bind(carList[position])
    }
}