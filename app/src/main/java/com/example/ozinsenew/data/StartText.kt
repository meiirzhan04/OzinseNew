package com.example.ozinsenew.data

import com.example.ozinsenew.R
import com.example.ozinsenew.R.drawable.ic_onboarding_1

data class StartText(
    val image: Int,
    val title: String,
    val description: String
)

val startTextList = listOf(
    StartText(
        image = ic_onboarding_1,
        title = "ÖZINŞE-ге қош келдің!",
        description = "Фильмдер, телехикаялар, ситкомдар, анимациялық жобалар, телебағдарламалар мен реалити-шоулар, аниме және тағы басқалары"
    ),
    StartText(
        image = R.drawable.ic_onboarding_2,
        title = "ÖZINŞE-ге қош келдің!",
        description = "Кез келген құрылғыдан қара Сүйікті фильміңді  қосымша төлемсіз телефоннан, планшеттен, ноутбуктан қара"
    ),
    StartText(
        image = R.drawable.ic_onboarding_3,
        title = "ÖZINŞE-ге қош келдің!",
        description = "Тіркелу оңай. Қазір тіркел де қалаған фильміңе қол жеткіз"
    ),
)