package com.example.moviesapp.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentEditBinding
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.model.Movie
import com.example.moviesapp.viewmodel.MovieViewModel
import java.lang.NullPointerException


class EditFragment : Fragment(R.layout.fragment_edit),MenuProvider {

    private var editBinding: FragmentEditBinding?= null
    private val binding get()=editBinding!!

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var currentMovie: Movie

    private val args: EditFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editBinding= FragmentEditBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)


        movieViewModel=(activity as MainActivity).movieViewModel
        currentMovie= args.movie!!

        binding.editNoteTitle.setText(currentMovie.movtitle)
        binding.editNoteDesc.setText(currentMovie.movdec)

        binding.editNoteFab.setOnClickListener{
           val movTitle = binding.editNoteTitle.text.toString().trim()
           val movDesc = binding.editNoteDesc.text.toString().trim()


            if(movTitle.isNotEmpty()){
                val movie= Movie(currentMovie.id,movTitle,movDesc)
                movieViewModel.updateMovie(movie)
                view.findNavController().popBackStack(R.id.homeFragment,false)

            }
            else{
                Toast.makeText(context, "please enter movie title", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteMovie(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Movie")
            setMessage("Do you want to delete this movie?")
            setPositiveButton("Delete"){_,_->
                movieViewModel.deleteMovie(currentMovie)
                Toast.makeText(context,"Movie deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when(menuItem.itemId){
          R.id.deleteMenu->{
            deleteMovie()
              true
          }
          else-> false

      }

    }
    override fun onDestroy() {
        super.onDestroy()
        editBinding=null
    }
}


