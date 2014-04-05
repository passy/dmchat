package net.rdrei.android.dmchat.screen;

import android.os.Bundle;

import net.rdrei.android.dmchat.R;
import net.rdrei.android.dmchat.view.ChatListView;
import net.rdrei.android.dmchat.view.MainBlueprint;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import flow.Flow;
import flow.Layout;
import mortar.Blueprint;
import mortar.ViewPresenter;

@Layout(R.layout.chat_list_view)
public class ChatListScreen implements Blueprint {
	@Override
	public String getMortarScopeName() {
		return getClass().getName();
	}

	@Override
	public Object getDaggerModule() {
		return new Module();
	}

	@dagger.Module(injects = ChatListView.class, addsTo = MainBlueprint.Module.class)
	static class Module {
	}

	@Singleton
	public static class Presenter extends ViewPresenter<ChatListView> {
		private final Flow mFlow;

		@Inject
		Presenter(Flow flow) {
			mFlow = flow;
		}

		@Override
		protected void onLoad(Bundle savedInstanceState) {
			super.onLoad(savedInstanceState);

			final ChatListView view = getView();
			if (view == null) return;

			// TODO: Do stuff with view.
		}
	}
}
