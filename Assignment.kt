fun main() {
    // Частина 1

    // Дано рядок у форматі "Student1 - Group1; Student2 - Group2; ..."

    val studentsStr =
        "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82"

// Завдання 1
// Заповніть словник, де:
// - ключ – назва групи
// - значення – відсортований масив студентів, які відносяться до відповідної групи

    val studentsGroups = HashMap<String, ArrayList<String>>()

// Ваш код починається тут

    val students = studentsStr.split(";")
    for (student in students) {
        val studentGroup = student.split(" - ")
        if (studentsGroups.containsKey(studentGroup[1])) studentsGroups[studentGroup[1]]?.add(studentGroup[0])
        else studentsGroups[studentGroup[1]] = arrayListOf(studentGroup[0])
    }

    for (studentsList in studentsGroups.values) studentsList.sort()

// Ваш код закінчується тут

    println("Завдання 1")
    println(studentsGroups)

    // Дано масив з максимально можливими оцінками

    val points = arrayListOf(12, 12, 12, 12, 12, 12, 12, 16)

    // Завдання 2
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – масив з оцінками студента (заповніть масив випадковими значеннями, використовуючи функцію `randomValue(maxValue: Int) -> Int`)

    fun randomValue(maxValue: Int): Int {
        return when ((1..6).random()) {
            1 -> kotlin.math.ceil(maxValue * 0.7).toInt()
            2 -> kotlin.math.ceil(maxValue * 0.9).toInt()
            3, 4, 5 -> maxValue
            else -> 0
        }
    }

    val studentPoints = HashMap<String, HashMap<String, ArrayList<Int>>>()

// Ваш код починається тут

    for (group in studentsGroups.keys) {
        studentPoints[group] = HashMap()
        for (student in studentsGroups[group]!!) {
            studentPoints[group]?.set(student, ArrayList())
            repeat(points.size) {
                studentPoints[group]!![student]!!.add(randomValue(points[it]))
            }
        }
    }

// Ваш код закінчується тут

    println("Завдання 2")
    println(studentPoints)

    // Завдання 3
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – сума оцінок студента

    val sumPoints = HashMap<String, HashMap<String, Int>>()


// Ваш код починається тут

    for (group in studentPoints.entries) {
        sumPoints[group.key] = HashMap()
        val studentsList = group.value.keys
        for (student in studentsList) {
            val pointsList = group.value[student]
            val pointsSum = pointsList?.sum()
            sumPoints[group.key]!![student] = pointsSum!!
        }
    }

// Ваш код закінчується тут

    println("Завдання 3")
    println(sumPoints)

    // Завдання 4
// Заповніть словник, де:
// - ключ – назва групи
// - значення – середня оцінка всіх студентів групи

    val groupAvg = HashMap<String, Float>()

// Ваш код починається тут

    for (group in sumPoints.entries) {
        val sumStudentsPoints = group.value.values.sum()
        val groupAvgPoint = sumStudentsPoints.toFloat() / group.value.size
        groupAvg[group.key] = groupAvgPoint
    }

// Ваш код закінчується тут

    println("Завдання 4")
    println(groupAvg)

    // Завдання 5
// Заповніть словник, де:
// - ключ – назва групи
// - значення – масив студентів, які мають >= 60 балів

    val passedPerGroup = HashMap<String, ArrayList<String>>()

// Ваш код починається тут

    for (group in sumPoints.entries) {
        passedPerGroup[group.key] = ArrayList()
        for (student in group.value.entries) {
            if (student.value >= 60) {
                passedPerGroup[group.key]?.add(student.key)
            }
        }
    }

// Ваш код закінчується тут

    println("Завдання 5")
    println(passedPerGroup)

}

