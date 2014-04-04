package net.rdrei.android.dmchat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.rdrei.android.dmchat.core.CanShowScreen;

import flow.Flow;
import mortar.Blueprint;
import mortar.Mortar;

public class MainView extends FrameLayout implements CanShowScreen<Blueprint> {
	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);

		Mortar.inject(context, this);
	}

	@Override
	public void showScreen(Blueprint screen, Flow.Direction direction) {
		throw new IllegalStateException("Not implemented");
	}
}
