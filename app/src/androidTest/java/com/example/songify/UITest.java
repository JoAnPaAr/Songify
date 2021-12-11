package com.example.songify;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //RF1 Se muestran las canciones en una lista
    @Test
    public void mostrarCancionesEnLista() {
        // Check that R.id.rv_Cancion exists
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_Cancion),
                        withParent(withParent(withId(R.id.nav_host_fragment_activity_main))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }

    //RF3 Se pueden elegir canciones
    @Test
    public void elegirCancionesEnLista() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_Cancion),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.boton_stop),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_nombreCancion), withText("Lying Together"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Lying Together")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_nombreArtista), withText("FKJ"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(withText("FKJ")));

        pressBack();
    }

    //RF4 Se pueden reproducir canciones
    //RF5 Se puede parar la reproduccion
    @Test
    public void reproducirPararCancion() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_Cancion),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.boton_play),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.boton_play),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        pressBack();
    }

    //RF6 Se puede cambiar la cancion que se esta reproduciendo
    @Test
    public void cambiarCancion() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_Cancion),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.boton_stop),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_nombreCancion), withText("Lying Together"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Lying Together")));

        pressBack();

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.rv_Cancion),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.boton_stop),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_nombreCancion), withText("Que Sera"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(not(withText("Lying Together"))));

        pressBack();
    }

    //RF7 Se puede repetir la cancion actual
    @Test
    public void repetirCancion() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_Cancion),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.boton_repeat),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.boton_stop),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.boton_repeat),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        pressBack();
    }

    //RF9 Se puede guardar una cancion en favoritos
    //RF10 Se puede quitar una cancion de favoritos
    @Test
    public void marcarDesmarcarFavoritosEnLista() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.iv_fav_cancion),
                        childAtPosition(
                                allOf(withId(R.id.cancionUnicaLineaLayout),
                                        childAtPosition(
                                                withId(R.id.rv_Cancion),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.iv_fav_cancion),
                        childAtPosition(
                                allOf(withId(R.id.cancionUnicaLineaLayout),
                                        childAtPosition(
                                                withId(R.id.rv_Cancion),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.iv_fav_cancion),
                        childAtPosition(
                                allOf(withId(R.id.cancionUnicaLineaLayout),
                                        childAtPosition(
                                                withId(R.id.rv_Cancion),
                                                2)),
                                5),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.listaFavoritos), withContentDescription("Favoritos"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavView),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Southern Man"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Favoritos)))),
                        isDisplayed()));
        textView.check(matches(withText("Southern Man")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Que Sera"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Favoritos)))),
                        isDisplayed()));
        textView2.check(matches(withText("Que Sera")));

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.iv_fav_cancion),
                        childAtPosition(
                                allOf(withId(R.id.cancionUnicaLineaLayout),
                                        childAtPosition(
                                                withId(R.id.rv_Favoritos),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Que Sera"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Favoritos)))),
                        isDisplayed()));
        textView3.check(matches(IsNot.not(withText("Southern Man"))));
    }

    //RF11 Se muestran las canciones favoritas
    @Test
    public void mostrarFavoritosEnLista() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.iv_fav_cancion),
                        childAtPosition(
                                allOf(withId(R.id.cancionUnicaLineaLayout),
                                        childAtPosition(
                                                withId(R.id.rv_Cancion),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.iv_fav_cancion),
                        childAtPosition(
                                allOf(withId(R.id.cancionUnicaLineaLayout),
                                        childAtPosition(
                                                withId(R.id.rv_Cancion),
                                                2)),
                                5),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.listaFavoritos), withContentDescription("Favoritos"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavView),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Southern Man"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Favoritos)))),
                        isDisplayed()));
        textView.check(matches(withText("Southern Man")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Lying Together"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Favoritos)))),
                        isDisplayed()));
        textView2.check(matches(withText("Lying Together")));

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.listaCanciones), withContentDescription("Canciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());
    }

    //RF12 Se muestran las canciones mas populares
    @Test
    public void mostrarPorRankingEnLista() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.listaExitos), withContentDescription("Exitos"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavView),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Lying Together"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Exitos)))),
                        isDisplayed()));
        textView.check(matches(withText("Lying Together")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_cancion_titulo), withText("Breathe"),
                        withParent(allOf(withId(R.id.cancionUnicaLineaLayout),
                                withParent(withId(R.id.rv_Exitos)))),
                        isDisplayed()));
        textView2.check(matches(withText("Breathe")));

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.listaCanciones), withContentDescription("Canciones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavView),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());
    }

    @Before
    public void addElements(){
        // Open Contextual Action Mode Overflow Menu
        openContextualActionModeOverflowMenu();
        //Perform a click() action on the view withText "Delete all" (Should be a R.string.* reference)
        onView(withText("Cargar")).perform(click());
    }

    @After
    public void deleteElements() {
        // Open Contextual Action Mode Overflow Menu
        openContextualActionModeOverflowMenu();
        //Perform a click() action on the view withText "Delete all" (Should be a R.string.* reference)
        onView(withText("Borrar Todo")).perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
