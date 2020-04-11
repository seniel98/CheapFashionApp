package com.jdpadron98carlosmc98.cheapfashionapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jdpadron98carlosmc98.cheapfashionapp.Login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);


    Context context =
            InstrumentationRegistry.getInstrumentation().getTargetContext();


    String emptyText = context.getString(R.string.emptyText);
    String signUpText = context.getString(R.string.signUpText);
    String forgotPass = context.getString(R.string.forgotPass);


    private void rotate() {

        Context context = ApplicationProvider.getApplicationContext();
        int orientation = context.getResources().getConfiguration().orientation;
        Activity activity = activityTestRule.getActivity();

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    @Test
    public void goToForgotPasswordPortrait() {

        // GIVEN
        // - Encontrandonos en la pantalla Login
        // - El boton de login estara habilitado
        // - Mostraremos los campos de texto vacios
        // - Estaran mostrados los textos correspondientes
        (onView(withId(R.id.loginButton))).check(matches(isEnabled()));
        (onView(withId(R.id.emailLoginText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.passLoginText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.signUpText))).check(matches(withText(signUpText)));
        (onView(withId(R.id.forgotPass))).check(matches(withText(forgotPass)));

        //WHEN
        // - Al pulsar el texto de Forgot Password
        (onView(withId(R.id.forgotPass))).perform(click());


        //THEN
        // - Se cargara la pantalla de Forgot Password
        // - Con el campo de texto vacio
        // - El boton de send habilitado
        (onView(withId(R.id.emailForgotPassText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.sendPassEmailButton))).check(matches(isEnabled()));

    }


    @Test
    public void goToSignUpPortrait() {

        // GIVEN
        // - Encontrandonos en la pantalla Login
        // - El boton de login estara habilitado
        // - Mostraremos los campos de texto vacios
        // - Estaran mostrados los textos correspondientes
        (onView(withId(R.id.loginButton))).check(matches(isEnabled()));
        (onView(withId(R.id.emailLoginText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.passLoginText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.signUpText))).check(matches(withText(signUpText)));
        (onView(withId(R.id.forgotPass))).check(matches(withText(forgotPass)));

        //WHEN
        // - Al pulsar el texto de Don’t have an account? Sign Up!
        (onView(withId(R.id.signUpText))).perform(click());


        //THEN
        // - Se cargara la pantalla de Sign Up
        // - Con los campos de texto vacio
        // - El boton de send habilitado
        (onView(withId(R.id.nameSignUpText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.emailSignUpText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.phoneNumberSignUpText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.passSignUpText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.signUpButton))).check(matches(isEnabled()));

    }


    @Test
    public void goToHomePortrait() {

        // GIVEN
        // - Encontrandonos en la pantalla Login
        // - El boton de login estara habilitado
        // - Mostraremos los campos de texto vacios
        // - Estaran mostrados los textos correspondientes
        (onView(withId(R.id.loginButton))).check(matches(isEnabled()));
        (onView(withId(R.id.emailLoginText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.passLoginText))).check(matches(withText(emptyText)));
        (onView(withId(R.id.signUpText))).check(matches(withText(signUpText)));
        (onView(withId(R.id.forgotPass))).check(matches(withText(forgotPass)));

        //WHEN
        // - Al pulsar el boton de Login
        (onView(withId(R.id.loginButton))).perform(click());


        //THEN
        // - Se cargara la pantalla de Home
        // - Con los productos en la tienda


    }



}
