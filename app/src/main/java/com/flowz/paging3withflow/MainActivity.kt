package com.flowz.paging3withflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.flowz.paging3withflow.adapter.RickynMortyPagingAdapter
import com.flowz.paging3withflow.databinding.ActivityMainBinding
import com.flowz.paging3withflow.databinding.RickNMortyItemBinding
import com.flowz.paging3withflow.ui.RickyAndMortyViewModel
import com.flowz.paging3withflow.utils.getConnectionType
import com.flowz.paging3withflow.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }


}