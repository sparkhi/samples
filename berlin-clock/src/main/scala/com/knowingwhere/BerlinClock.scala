package com.knowingwhere

/**
 * The Berlin Uhr (Clock) is a rather strange way to show the time.
 * On the top of the clock there is a yellow lamp that blinks on/off every two seconds.
 * The time is calculated by adding rectangular lamps.
 *
 * The top two rows of lamps are red. These indicate the hours of a day. In the top row there are 4 red lamps.
 * Every lamp represents 5 hours. In the lower row of red lamps every lamp represents 1 hour.
 * So if two lamps of the first row and three of the second row are switched on that indicates 5+5+3=13h or 1 pm.
 *
 * The two rows of lamps at the bottom count the minutes. The first of these rows has 11 lamps, the second 4.
 * In the first row every lamp represents 5 minutes.
 * In this first row the 3rd, 6th and 9th lamp are red and indicate the first quarter, half and last quarter of an hour.
 * The other lamps are yellow. In the last row with 4 lamps every lamp represents 1 minute.
 *
 * The lamps are switched on from left to right.
 *
 * Y = Yellow
 * R = Red
 * O = Off
 */
object BerlinClock {
  def convertToBerlinTime(clocktime: String) = {
    val timeParts = clocktime.split(":").map(_.toInt)
    Array(
      seconds(timeParts(2)),
      topHours(timeParts(0)),
      bottomHours(timeParts(0)),
      topMinutes(timeParts(1)),
      bottomMinutes(timeParts(1))
    )
  }

  //In the last row with 4 lamps every lamp represents 1 minute.
  def bottomMinutes(num: Int) = {
    val on = num - num / 5 * 5
    "Y" * on + "O" * (4 - on)
  }

  //In the first row every lamp represents 5 minutes.
  //Every 3rd lamp is Red, others yellow
  def topMinutes(num: Int) = {
    val on = num / 5
    val onGroups = on / 3
    val onLeftOver = on - onGroups * 3
    val off = 11 - on
    "YYR" * onGroups + "Y" * onLeftOver + "O" * off
  }

  // In the lower row of red lamps every lamp represents 1 hour
  def bottomHours(num: Int) = {
    val on = num - num / 5 * 5
    "R" * on + "O" * (4 - on)
  }

  // In the top row there are 4 red lamps every lamp represents 5 hours.
  def topHours(num: Int) = {
    val on = num / 5
    "R" * on + "O" * (4 - on)
  }

  def seconds(num: Int) = {
    if (num % 2 == 0) "Y" else "O"
  }
}
