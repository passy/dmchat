package net.rdrei.android.dmchat.view;

import net.rdrei.android.dmchat.core.ApplicationModule;
import net.rdrei.android.dmchat.core.FlowOwner;
import net.rdrei.android.dmchat.core.MainScope;
import net.rdrei.android.dmchat.screen.ChatListScreen;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flow.Flow;
import flow.Parcer;
import mortar.Blueprint;

public class MainBlueprint implements Blueprint {
	@Override
	public String getMortarScopeName() {
		return ((Object) this).getClass().getName();
	}

	@Override
	public Object getDaggerModule() {
		return null;
	}

	@dagger.Module(
			injects = MainView.class,
			addsTo = ApplicationModule.class,
			library = true
	)
	public static class Module {
		@Provides @MainScope
		Flow provideFlow(Presenter presenter) {
			return presenter.getFlow();
		}
	}

	@Singleton
	public static class Presenter extends FlowOwner<Blueprint, MainView> {
		@Inject
		Presenter(final Parcer<Object> flowParcer) {
			super(flowParcer);
		}

		@Override
		protected Blueprint getFirstScreen() {
			return new ChatListScreen();
		}
	}
}
