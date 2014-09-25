package com.example.fragmenttabdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabFragment tabFragment = new TabFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.container, tabFragment)
				.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static class MyFragment extends Fragment {

		public static MyFragment newInstance(String title) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putString("title", title);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment, container, false);
			TextView textView = (TextView) view.findViewById(R.id.text);
			textView.setText(getArguments().getString("title"));
			return view;
		}
	}

	public static class TabFragment extends Fragment {
		private FragmentTabHost mTabHost;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.tab_fragment, container, false);
			mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
			mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
			Bundle bundle = new Bundle();
			bundle.putString("title", "simple");
			mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"), MyFragment.class,
					bundle);
			bundle = new Bundle();
			bundle.putString("title", "contact");
			mTabHost.addTab(mTabHost.newTabSpec("contact").setIndicator("Contact"),
					MyFragment.class, bundle);
			bundle = new Bundle();
			bundle.putString("title", "custom");
			mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("Custom"), MyFragment.class,
					bundle);
			return view;
		}
	}
}
