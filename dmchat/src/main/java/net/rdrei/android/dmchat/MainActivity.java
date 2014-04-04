package net.rdrei.android.dmchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import net.rdrei.android.dmchat.view.MainBlueprint;
import net.rdrei.android.dmchat.view.MainView;

import mortar.Mortar;
import mortar.MortarActivityScope;
import mortar.MortarScope;

public class MainActivity extends Activity {

	private final static String TAG = "MAIN";
	private MortarActivityScope mActivityScope;
	private Context mMortarContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (isWrongInstance()) {
			finish();
			Log.w(TAG, "Shutting down wrong instance.");
			return;
		}

		final MortarScope parentScope = ((DMChatApplication) getApplication()).getRootScope();
		mActivityScope = Mortar.requireActivityScope(parentScope, new MainBlueprint());
		mMortarContext = mActivityScope.createContext(this);
		Mortar.inject(mMortarContext, this);

		mActivityScope.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// TODO: Butterknife
		final MainView mainView = (MainView) findViewById(R.id.container);
		// mMainFlow = mainView.get...
	}

	/**
	 * Dev tools and the play store (and others?) launch with a different intent, and so
	 * lead to a redundant instance of this activity being spawned. <a
	 * href="http://stackoverflow.com/questions/17702202/find-out-whether-the-current-activity-will-be-task-root-eventually-after-pendin"
	 * >Details</a>.
	 */
	private boolean isWrongInstance() {
		if (!isTaskRoot()) {
			final Intent intent = getIntent();
			final boolean isMainAction = intent.getAction() != null &&
					intent.getAction().equals(Intent.ACTION_MAIN);
			return intent.hasCategory(Intent.CATEGORY_LAUNCHER) && isMainAction;
		}
		return false;
	}
}
