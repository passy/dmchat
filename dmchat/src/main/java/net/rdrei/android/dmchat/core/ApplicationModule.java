package net.rdrei.android.dmchat.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flow.Parcer;

@Module(library = true)
public class ApplicationModule {
	@Provides
	@Singleton
	Gson provideGson() {
		return new GsonBuilder().create();
	}

	@Provides
	@Singleton
	Parcer<Object> provideParcer(final Gson gson) {
		return new GsonParcer<>(gson);
	}
}
