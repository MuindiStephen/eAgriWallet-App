package com.example.farming.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.farming.R
import com.example.farming.adapter.SuppliersAdapter
import com.example.farming.databinding.FragmentDashboardBinding
import com.example.farming.model.Supplies
import com.google.firebase.database.*

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var adapter: SuppliersAdapter

    var itemList: ArrayList<Supplies>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        setUpBinding()


        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRecyclerView = view.findViewById(R.id.recyclerView)





        /*

        itemList = arrayListOf()
        reference = mFirebaseDatabase.getReference("Suppliers")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val item = i.getValue(Supplies::class.java)
                        itemList!!.add(item!!)
                    }
                    val adapter = SuppliersAdapter(activity!!.applicationContext,itemList!!)
                    mRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }

        })


    }

         */
    }

    private fun setUpBinding() {
        binding.seeAllBids.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_listMateialBidsFragment)
        }
    }
}