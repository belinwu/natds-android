package com.natura.android.sample.components

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.android.synthetic.main.activity_card.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardActivityFunctionalTest {

    private lateinit var scenario: ActivityScenario<CardActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(CardActivity::class.java)
    }

    @Test
    fun checksIfEnabledElevationIsTrueAtCard() {
        scenario.onActivity {
            val card = it.card
            val enabledElevation = card.getEnabledElevation()

            Truth.assertThat(enabledElevation).isTrue()
        }
    }

    @Test
    fun checksIfEnabledRadiusIsTrueAtCard() {
        scenario.onActivity {
            val card = it.card
            val enabledRadius = card.getEnabledRadius()

            Truth.assertThat(enabledRadius).isTrue()
        }
    }

    @Test
    fun checksIfElevationGreaterThen0AtCard() {
        scenario.onActivity {
            val card = it.card
            val elevation = card.elevation

            Truth.assertThat(elevation > 0).isTrue()
        }
    }

    @Test
    fun checksIfRadiusGreaterThen0AtCard() {
        scenario.onActivity {
            val card = it.card
            val radius = card.radius

            Truth.assertThat(radius > 0).isTrue()
        }
    }
}
