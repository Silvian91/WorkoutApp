package com.example.workoutapp.di;

import android.app.Application;

import com.example.workoutapp.WorkoutApplication;
import com.example.workoutapp.di.addroutine.AddRoutineComponent;
import com.example.workoutapp.di.addroutine.AddRoutineModule;
import com.example.workoutapp.di.addworkout.AddWorkoutComponent;
import com.example.workoutapp.di.addworkout.AddWorkoutModule;
import com.example.workoutapp.di.showroutine.ShowRoutineComponent;
import com.example.workoutapp.di.showroutine.ShowRoutineModule;
import com.example.workoutapp.di.showworkout.ShowWorkoutComponent;
import com.example.workoutapp.di.showworkout.ShowWorkoutModule;

import dagger.BindsInstance;
import dagger.Component;

/**
 * if you wanna make a new feature:
 * <p>
 * - make a new component interface to inject to your new activities and fragments
 * - make a new module to describe how to create the dependencies in your activities and fragments
 * <p>
 * - add a plus method to this app component
 * - add a create method to FlashCardComponentProvider
 * <p>
 * the rest was groundwork that makes it easier to work with Dagger
 */

@Component(modules = {AndroidModule.class, CompositeDisposableModule.class})
public interface AppComponent {
    void inject(WorkoutApplication workoutApplication);

    AddWorkoutComponent plus(AddWorkoutModule addWorkoutModule);

    ShowWorkoutComponent plus(ShowWorkoutModule showWorkoutModule);

    AddRoutineComponent plus(AddRoutineModule addRoutineModule);

    ShowRoutineComponent plus(ShowRoutineModule showRoutineModule);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
