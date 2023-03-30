package com.practicum.sharedpreferencearraylisthistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(val listener: ClickListener) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    var courseModalArrayList = ArrayList<CourseModal>()

    fun interface ClickListener {
        fun onClick(cours: CourseModal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.course_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseModalArrayList[position], listener)
    }

    override fun getItemCount() = courseModalArrayList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseNameTV: TextView
        private val courseDescTV: TextView

        init {
            courseNameTV = itemView.findViewById(R.id.idTVCourseName)
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription)
        }

        fun bind(curs: CourseModal, listener: ClickListener) {
            courseNameTV.text = curs.courseName
            courseDescTV.text = curs.courseDescription
            itemView.setOnClickListener {
                listener.onClick(curs)
            }
        }
    }
}