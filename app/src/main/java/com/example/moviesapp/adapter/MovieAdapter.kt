package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.FragmentEditBinding
import com.example.moviesapp.databinding.MovieLayoutBinding
import com.example.moviesapp.fragments.HomeFragmentDirections
import com.example.moviesapp.model.Movie

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val itemBinding: MovieLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback= object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id== newItem.id &&
                    oldItem.movdec==newItem.movdec &&
                    oldItem.movtitle== newItem.movtitle
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
          return oldItem==newItem
        }
    }
    val differ=AsyncListDiffer (this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       val currentMov= differ.currentList[position]

        holder.itemBinding.noteTitle.text=currentMov.movtitle
        holder.itemBinding.noteDesc.text=currentMov.movdec

        holder.itemView.setOnClickListener{
            val dir= HomeFragmentDirections.actionHomeFragmentToEditFragment(currentMov)
            it.findNavController().navigate(dir)
        }
    }
}