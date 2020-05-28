package ru.lopatin.mvvm_pattern_kotlin.storage

class StorageImitation(
     val professorDoul: ArrayList<String> = ArrayList(),
     val masterAndMargarita: ArrayList<String> = ArrayList()
) {


    init {
        professorDoul.let {
            it.add(
                "Посреди комнаты - большой прозекторский стол. Рядом со столом - стеклянный ящик; в нем пульсировало человеческое сердце. От сердца шли трубки к баллонам.\n" +
                        "    Лоран повернула голову в сторону и вдруг увидала нечто, заставившее ее вздрогнуть, как от электрического удара.\n" +
                        "    На нее смотрела человеческая голова - одна голова без туловища."
            )
            it.add(
                "Она была прикреплена к квадратной стеклянной доске. Доску поддерживали четыре высокие блестящие металлические ножки. От перерезанных артерий и вен, через отверстия в стекле," +
                        " шли, соединившись уже попарно, трубки к баллонам. Более толстая трубка выходила из горла и сообщалась с большим цилиндром. Цилиндр и баллоны были снабжены кранами, манометрами, термометрами и неизвестными Лоран приборами.\n" +
                        "    Голова внимательно и скорбно смотрела на Лоран, мигая веками. Не могло быть сомнения: голова жила, отделенная от тела, самостоятельной и сознательной жизнью."
            )
            it.add(
                "Несмотря на потрясающее впечатление, Лоран не могла не заметить, что эта голова удивительно похожа на голову недавно умершего известного ученого-хирурга, профессора Доуэля, прославившегося своими опытами оживления органов, " +
                        "вырезанных из свежего трупа. Лоран не раз была на его блестящих публичных лекциях, и ей хорошо запомнился этот высокий лоб, характерный профиль, волнистые, посеребренные сединой густые русые волосы, голубые глаза... " +
                        "Да, это была голова профессора Доуэля. Только губы и нос его стали тоньше, виски и щеки втянулись, глаза глубже запали в орбиты и белая кожа приобрела желто-темный оттенок мумии. Но в глазах была жизнь, была мысль.\n" +
                        "    Лоран как зачарованная не могла оторвать взгляда от этих голубых глаз."
            )
            it.add(
                " Голова беззвучно шевельнула губами.\n" +
                        "    Это было слишком для нервов Лоран. Она была близка к обмороку. Негр поддержал ее и вывел из лаборатории.\n" +
                        "    - Это ужасно, это ужасно... - повторяла Лоран, опустившись в кресло.\n" +
                        "    Профессор Керн молча барабанил пальцами по столу.\n" +
                        "    - Скажите, неужели это голова?.."
            )
            it.add(
                " - Профессора Доуэля? Да, это его голова. Голова Доуэля, моего умершего уважаемого коллеги, возвращенная мною к жизни." +
                        " К сожалению, я мог воскресить одну голову. Не все дается сразу. Бедный Доуэль страдал неизлечимым пока недугом." +
                        " Умирая, он завещал свое тело для научных опытов, которые мы вели с ним вместе. \"Вся моя жизнь была посвящена науке. " +
                        "Пусть же науке послужит и моя смерть. Я предпочитаю, чтобы в моем трупе копался друг-ученый, а не могильный червь\" - вот какое завещание оставил профессор Доуэль." +
                        " И я получил его тело. Мне удалось не только оживить его сердце, но и воскресить сознание, воскресить \"душу\", говоря языком толпы. " +
                        "Что же тут ужасного? Люди считали до сих пор ужасной смерть. Разве воскресение из мертвых не было тысячелетней мечтой человечества?\n"
            )
            it.add(
                "- Я бы предпочла смерть такому воскресению.\n" +
                        "    Профессор Керн сделал неопределенный жест рукой.\n" +
                        "    - Да, оно имеет свои неудобства для воскресшего. Бедному Доуэлю было бы неудобно показаться публике в таком... неполном виде. " +
                        "Вот почему мы обставляем тайной этот опыт. Я говорю \"мы\", потому что таково желание самого Доуэля. Притом опыт еще не доведен до конца.\n"
            )
        }

        masterAndMargarita.let {
            it.add(
                "Вряд ли теперь узнали бы Коровьева-Фагота, самозванного переводчика при таинственном и не нуждающемся ни в каких переводах консультанте, в том, кто теперь летел непосредственно рядом" +
                        " с Воландом по правую руку подруги мастера. На месте того, кто в драной цирковой одежде покинул Воробьевы горы под именем Коровьева-Фагота, теперь скакал, тихо звеня золотою цепью повода, темно-фиолетовый рыцарь" +
                        " с мрачнейшим и никогда не улыбающимся лицом. Он уперся подбородком в грудь, он не глядел на луну, он не интересовался землею под собою, он думал о чем-то своем, летя рядом с Воландом."
            )
            it.add(
                "– Почему он так изменился? – спросила тихо Маргарита под свист ветра у Воланда.\n" +
                        "– Рыцарь этот когда-то неудачно пошутил, – ответил Воланд, поворачивая к Маргарите свое лицо с тихо горящим глазом, – его каламбур, который он сочинил, разговаривая о свете и тьме, был не совсем хорош. " +
                        "И рыцарю пришлось после этого прошутить немного больше и дольше, нежели он предполагал. Но сегодня такая ночь, когда сводятся счеты. Рыцарь свой счет оплатил и закрыл!"
            )
            it.add(
                "Ночь оторвала и пушистый хвост у Бегемота, содрала с него шерсть и расшвыряла ее клочья по болотам. Тот, кто был котом, потешавшим князя тьмы, теперь оказался худеньким юношей, демоном-пажом, " +
                        "лучшим шутом, какой существовал когда-либо в мире. Теперь притих и он и летел беззвучно, подставив свое молодое лицо под свет, льющийся от луны."
            )
            it.add(
                "Сбоку всех летел, блистая сталью доспехов, Азазелло. Луна изменила и его лицо. Исчез бесследно нелепый безобразный клык, и кривоглазие оказалось фальшивым. Оба глаза Азазелло были одинаковые, " +
                        "пустые и черные, а лицо белое и холодное. Теперь Азазелло летел в своем настоящем виде, как демон безводной пустыни, демон-убийца."
            )
            it.add(
                "Себя Маргарита видеть не могла, но она хорошо видела, как изменился мастер. Волосы его белели теперь при луне и сзади собирались в косу, и она летела по ветру. Когда ветер отдувал плащ от ног мастера, " +
                        "Маргарита видела на ботфортах его то потухающие, то загорающиеся звездочки шпор. Подобно юноше-демону, мастер летел, не сводя глаз с луны, но улыбался ей, как будто знакомой хорошо и любимой, и что-то, по приобретенной в комнате N 118-й привычке, сам себе бормотал."
            )
            it.add(
                "И, наконец, Воланд летел тоже в своем настоящем обличье. Маргарита не могла бы сказать, из чего сделан повод его коня, и думала, что возможно, что это лунные цепочки и самый конь – только глыба мрака, " +
                        "и грива этого коня – туча, а шпоры всадника – белые пятна звезд."
            )
        }
    }
}