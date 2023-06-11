package id.ac.umn.rumahku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.umn.rumahku.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var database: DatabaseReference
    lateinit var adapter: RumahAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main, container, false)

        database = FirebaseDatabase.getInstance().reference.child("rumah")
        binding.recycle.layoutManager = LinearLayoutManager(context)
        val option = FirebaseRecyclerOptions.Builder<Rumah>()
            .setQuery(database, Rumah::class.java).build()
        adapter = RumahAdapter(option)
        binding.recycle.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}