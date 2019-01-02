package com.kowalski.damian.shoppinglist.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.kowalski.damian.shoppinglist.R
import com.kowalski.damian.shoppinglist.ui.lists.ListsFragment
import com.kowalski.damian.shoppinglist.ui.lists.ListsFragment_
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.EBean

@EBean
@EActivity
class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager

    var listsFragment: ListsFragment = ListsFragment_()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(listsFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }
}
