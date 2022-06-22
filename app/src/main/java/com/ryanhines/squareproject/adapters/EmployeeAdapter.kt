package com.ryanhines.squareproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ryanhines.squareproject.R
import com.ryanhines.squareproject.models.Employee

class EmployeeAdapter(
    private val context: Context,
    private val employees: List<Employee>
) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

     inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.tvEmployeeFullName)
        private val team: TextView = view.findViewById(R.id.tvEmployeeTeam)
        private val img: ImageView = view.findViewById(R.id.ivEmployeePhoto)
        val card: CardView = view.findViewById(R.id.cardEmployee)

         /**
          * Populates the text and image views with the employee data
          */
        fun bindData(employee: Employee) {
             name.text = employee.fullName
             team.text = employee.team

             // Load and cache the employee small photo to disk
             val placeholderImg = R.drawable.avatar_placeholder_round
             Glide.with(view.context)
                 .load(employee.smallPhotoURL)
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .error(placeholderImg)
                 .placeholder(placeholderImg)
                 .circleCrop()
                 .into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.card_employee,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = employees[position]
        holder.bindData(employee)

        // Alternate colors of employee cards to give them separation within the recycler view
        if (position % 2 == 0) {
            holder.card.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.cardColor
                )
            )
        } else {
            holder.card.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.cardColorSecondary
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }
}