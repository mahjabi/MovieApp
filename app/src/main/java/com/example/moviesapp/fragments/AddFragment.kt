package com.example.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentAddBinding
import com.example.moviesapp.model.Movie
import com.example.moviesapp.viewmodel.MovieViewModel

class AddFragment : Fragment(R.layout.fragment_add),MenuProvider {

    private var addBinding: FragmentAddBinding?=null
    private val binding get()=addBinding!!

    private lateinit var moviesViewModel: MovieViewModel
    private lateinit var addMovieView: View
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addBinding=FragmentAddBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)


        moviesViewModel=(activity as MainActivity).movieViewModel
        addMovieView=view
    }
    private fun saveMovie(view: View){
        val movieTitle=binding.addNoteTitle.text.toString().trim()
        val movieDesc=binding.addNoteDesc.text.toString().trim()

        if(movieTitle.isNotEmpty()){
            val movie= Movie(0,movieTitle,movieDesc)
            moviesViewModel.addMovie(movie)

            Toast.makeText(addMovieView.context, "Movie saved", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)

        }
        else{
            Toast.makeText(addMovieView.context, "please enter movie title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_movie,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when (menuItem.itemId){
          R.id.saveMenu->{
              saveMovie(addMovieView)
              true
          }
          else -> false

      }
    }

    override fun onDestroy() {
            super.onDestroy()
            addBinding=null
    }
}