# LabsProgramming
My programming labs

## Pokedex 
Pokemon.jar-based classes describing multiple Pokemon and their abilities.
 - Attempt.java - main class, program starts from here
 - Lab.jar - execute it with Pokemon.jar (On UNIX:java -cp Pokemon.jar:Lab.jar Attempt)
 
## Fslu
Programmed Muminhuset-based story whith some unexpected outcomes
 - Program.java / Program.class - main class, program starts from here
 - Based on: "Снифф глянул на них и с удовлетворением отметил про себя, что они намного меньше его. От этого он сразу подобрел и снисходительно сказал: Так Тофслу и Вифслу приняли в Муми-дом. Они ни перед кем не задирали носа и почти все время бродили по долине рука об руку. Чемодан они повсюду таскали с собой. Но когда наступили сумерки, они забеспокоились, забегали по всем лестницам и в конце концов спрятались под ковер."
 
## FsluExtended
Great Extencion to the Fslu-project with new storylines and new heroes!
 - Program.java / Program.class - main class, program starts from here
 - Based on: "Вместе с Хемулем они толкли кардамон для сладкого пирога. Снифф весь вспыхнул и вскинул голову. Хемуль подбежал маленькими шажками к крышке и приветливо крикнул: Тофсла и Вифсла высунули головы из картошки и посмотрели на него. Тофсла и Вифсла поднялись из подпола в гостиную. Снифф глянул на них и с удовлетворением отметил про себя, что они намного меньше его. От этого он сразу подобрел и снисходительно сказал: Так Тофслу и Вифслу приняли в Муми-дом. Они ни перед кем не задирали носа и почти все время бродили по долине рука об руку. Чемодан они повсюду таскали с собой. Но когда наступили сумерки, они забеспокоились, забегали по всем лестницам и в конце концов спрятались под ковер. Тофсла вытаращил глаза, оскалил зубы и напыжился, как только мог. Хемуль прибежал к Муми-маме и сказал: Но Тофсла и Вифсла уже спрятались в ящик комода и ни за что не хотели вылезать оттуда. Муми-папа покачал головой и пошел в дровяной сарай за ружьем. На дворе уже было по-августовски темно, сад окутали бархатисто-черные тени. Мрачно шумело в лесу, мелькали со своими карманными фонариками светлячки. Страшновато было Муми-папе идти за ружьем. А вдруг эта самая Морра подстерегает тебя за кустом? А ты даже не знаешь, какая она из себя, и главное -- какого она роста. Вернувшись на веранду, Муми-папа загородил дверь диваном и объявил: Ящик безмолвствовал. Муми-папа вытянул его, чтобы посмотреть, не похищены ли уже Тофсла и Вифсла. Но они мирно спали. Чемодан лежал с ними рядом."
 
## Lab5 (reworked into Lab6)
Console application for working with collection and database. You can fill the Data.csv with information about objects and the program will load them after launching. Then use "help" command and enjoy!
 - Name of the database file must be contained in the Environment Variable "TEMP"
 - Database file must be in CSV format
 - Main.java / Main.class - main class, program starts from here

## Lab6
Client/Server realization of Lab5 program
 - Name of the database file must be contained in the Environment Variable "TEMP" on the Client side
 - Database file must be in CSV format and it must be stored on the Server side
 - Clent.java / Client.class - main client class, program starts from here
 - Server.java / Server.class - main server class, program starts from here
 - To make it work correctly you need Logback libraries installed on your Server!!!