package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite

class AboutAdvancedOptions extends KoanSuite {
  koan("Option is more than just a replacement of null, its also a collection") {
    Some(10) map { _ + 10} should be(Some(20))
    Some(10) filter { _ == 10} should be(Some(10))
    Some(Some(10)) flatMap { _ map { _ + 10}} should be(Some(20))

    var newValue1 = 0
    Some(20) foreach { newValue1 = _}
    newValue1 should be(20)

    var newValue2 = 0
    None foreach { newValue2 = _}
    newValue2 should be(0)
  }

  koan("Using Option to avoid if checks for null") {
    //the ugly version
    def makeFullName(firstName: String, lastName: String) = {
      if (firstName != null) {
        if (lastName != null) {
          firstName + " " + lastName
        } else {
          null
        }
      } else {
        null
      }
    }
    makeFullName("Nilanjan", "Raychaudhuri") should be("Nilanjan Raychaudhuri")
    makeFullName("Nilanjan", null) should be(null)

    //the pretty version
    def makeFullNamePrettyVersion(firstName: Option[String], lastName: Option[String]) = {
      firstName flatMap {
        fname =>
          lastName flatMap {
            lname =>
              Some(fname + " " + lname)
          }
      }
    }
    makeFullNamePrettyVersion(Some("Nilanjan"), Some("Raychaudhuri")) should be(Some("Nilanjan Raychaudhuri"))
    makeFullNamePrettyVersion(Some("Nilanjan"), None) should be(None)
  }

  koan("Using in for comprehension") {
    val values = List(Some(10), Some(20), None, Some(15))
    val newValues = for {
      someValue <- values
      value <- someValue
    } yield value
    newValues should be(List(10, 20, 15))
  }
}
