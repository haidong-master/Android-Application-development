package com.example.preferencefragmentdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

public class PrefsFragment extends PreferenceFragment implements OnPreferenceChangeListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.pre_setting);
		Preference NamePreference = findPreference("name");
		SharedPreferences sharedPreferences = NamePreference
				.getSharedPreferences();
		NamePreference.setSummary(sharedPreferences.getString(
				"name", ""));
		if (sharedPreferences.getBoolean("save_info", false))
			NamePreference.setEnabled(true);
		else
			NamePreference.setEnabled(false);
		NamePreference.setOnPreferenceChangeListener(this);
	}
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		preference.setSummary(String.valueOf(newValue));
		return true;
	}
	
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
		Preference preference) {
		if ("save_info".equals(preference.getKey())) {
			findPreference("name").setEnabled(
					!findPreference("name").isEnabled());
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
}
