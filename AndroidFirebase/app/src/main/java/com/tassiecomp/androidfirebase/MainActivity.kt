package com.tassiecomp.androidfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tassiecomp.androidfirebase.Fragments.ChatsFragment
import com.tassiecomp.androidfirebase.Fragments.SearchFragment
import com.tassiecomp.androidfirebase.Fragments.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        val toolbar:Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        val tabLayout:TabLayout = findViewById(R.id.tab_layout)
        val viewPager:ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        viewPagerAdapter.addFragment(ChatsFragment(),"Chats")
        viewPagerAdapter.addFragment(SearchFragment(),"Search")
        viewPagerAdapter.addFragment(SettingFragment(),"Setting")

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu_main,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_setting -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    internal class ViewPagerAdapter(fragmentManager: FragmentManager):
        FragmentPagerAdapter(fragmentManager){

        private val fragments:ArrayList<Fragment>
        private val titles:ArrayList<String>

        init{
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }

        override fun getCount(): Int {
            return fragments.size
        }


        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment:Fragment, title:String){
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(i: Int): CharSequence? {
            return titles[i]
        }


    }
}