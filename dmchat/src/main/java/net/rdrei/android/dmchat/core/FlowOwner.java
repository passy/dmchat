package net.rdrei.android.dmchat.core;

import android.os.Bundle;
import android.view.View;

import flow.Backstack;
import flow.Flow;
import flow.Parcer;
import mortar.Blueprint;
import mortar.ViewPresenter;

public abstract class FlowOwner<S extends Blueprint, V extends View & CanShowScreen<S>> extends ViewPresenter<V> implements Flow.Listener {
	private static final String FLOW_KEY = "FLOW_KEY";
	private final Parcer<Object> mParcer;
	private Flow mFlow;

	protected FlowOwner(Parcer<Object> parcer) {
		mParcer = parcer;
	}

	@Override
	protected void onLoad(Bundle savedInstanceState) {
		super.onLoad(savedInstanceState);

		if (mFlow == null) {
			Backstack backstack;

			if (savedInstanceState != null) {
				backstack = Backstack.from(savedInstanceState.getParcelable(FLOW_KEY), mParcer);
			} else {
				backstack = Backstack.fromUpChain(getFirstScreen());
			}

			mFlow = new Flow(backstack, this);
		}
	}

	@Override
	public void go(Backstack backstack, Flow.Direction direction) {
		final S newScreen = (S) backstack.current().getScreen();
		showScreen(newScreen, direction);
	}

	public final Flow getFlow() {
		return mFlow;
	}

	protected void showScreen(S newScreen, Flow.Direction direction) {
		final V view = getView();

		if (view == null) return;

		view.showScreen(newScreen, direction);
	}

	/** The first screen shown by the presenter implementing this. */
	protected abstract S getFirstScreen();
}
