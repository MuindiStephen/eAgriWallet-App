package com.example.farming.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.farming.R
import com.example.farming.adapter.FarmersAdapter
import com.example.farming.data.RetrofitInstance
import com.example.farming.data.SuppliersDTOItem
import com.example.farming.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var mRecyclerView: RecyclerView

   // private lateinit var mFirebaseDatabase: FirebaseDatabase
   // private lateinit var reference: DatabaseReference

     val suppliersAdapter by lazy { FarmersAdapter() }

    // var itemList: ArrayList<Supplies>? = null


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

        //mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRecyclerView = view.findViewById(R.id.recyclerView)


        RetrofitInstance.api.getAllSuppliers().enqueue(object : retrofit2.Callback<ArrayList<SuppliersDTOItem>>{
            override fun onResponse(
                call: Call<ArrayList<SuppliersDTOItem>>,
                response: Response<ArrayList<SuppliersDTOItem>>
            ) {
                if (response.isSuccessful) {
                    suppliersAdapter.submitList(response.body())
                    mRecyclerView.adapter = suppliersAdapter
                }
            }

            override fun onFailure(call: Call<ArrayList<SuppliersDTOItem>>, t: Throwable) {
                Toast.makeText(requireContext(), "No available supplies",Toast.LENGTH_SHORT).show()
            }

        })


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

        binding.bidSupplyMaterial.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment2_to_itemBiddingDetailFragment2)
        }

        binding.seeAllBids.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment2_to_listMateialBidsFragment2)
        }
    }
}