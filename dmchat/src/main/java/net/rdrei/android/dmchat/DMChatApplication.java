package net.rdrei.android.dmchat;

import android.app.Application;

import net.rdrei.android.dmchat.core.ApplicationModule;

import dagger.ObjectGraph;
import mortar.Mortar;
import mortar.MortarScope;

public class DMChatApplication extends Application {

	private MortarScope mRootScope;

	@Override
	public void onCreate() {
		super.onCreate();

		mRootScope = Mortar.createRootScope(BuildConfig.DEBUG, ObjectGraph.create(new ApplicationModule()));
	}

	public MortarScope getRootScope() {
		return mRootScope;
	}
}
