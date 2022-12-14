package com.codialstudent.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.codialstudent.movieapp.R
import com.codialstudent.movieapp.databinding.RvItemBinding
import com.codialstudent.movieapp.models.MovieData

class RvAdapter(val context: Context, val list: ArrayList<MovieData>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: RvItemBinding) : ViewHolder(itemRvBinding.root) {
        var lastPosition =  -1
        fun onBind(movie: MovieData, position: Int) {
            itemRvBinding.tvName.text = movie.name
            itemRvBinding.tvActors.text = movie.actors
            itemRvBinding.tvDate.text = movie.date
            val rvAnim = if (position>lastPosition){
                AnimationUtils.loadAnimation(context,R.anim.up_from_bottom)
            }else{
                AnimationUtils.loadAnimation(context,R.anim.down_from_top)
            }
            lastPosition = position

            itemRvBinding.root.startAnimation(rvAnim)

            itemRvBinding.btnEdit.setOnClickListener {
                rvClick.editMovie(movie, position)
            }
            itemRvBinding.btnDelete.setOnClickListener {
                rvClick.deleteMovie(movie, position)
            }

            itemRvBinding.root.setOnClickListener {
               rvClick.mainClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) = holder.onBind(list[position], position)


    override fun getItemCount(): Int = list.size

    override fun onViewDetachedFromWindow(holder: Vh) {
        super.onViewDetachedFromWindow(holder)
        holder.itemRvBinding.root.clearAnimation()
    }

}

interface RvClick {
    fun editMovie(movie: MovieData, position: Int)
    fun deleteMovie(movie: MovieData, position: Int)
    fun mainClick(movie: MovieData)
}