package com.showka.everpub.novelmarkup

enum class NmHanGrade(private val value: Int) {
    /** 幼稚園 */
    PRE_ELEMENTARY(0),
    /** 小学1年生 */
    ELEMENTARY_1(1),
    /** 小学2年生 */
    ELEMENTARY_2(2),
    /** 小学3年生 */
    ELEMENTARY_3(3),
    /** 小学4年生 */
    ELEMENTARY_4(4),
    /** 小学5年生 */
    ELEMENTARY_5(5),
    /** 小学6年生 */
    ELEMENTARY_6(6),
    /** 中年生 */
    MIDDLE(7);

    fun getValue(): Int = this.value

    fun isHigher(other: NmHanGrade): Boolean = (this.value > other.value)
}