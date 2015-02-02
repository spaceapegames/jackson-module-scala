package com.fasterxml.jackson.module.scala.deser

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, JsonScalaEnumeration, Weekday}
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

class EnumContainer {

	var day = Weekday.Fri
}

class WeekdayType extends TypeReference[Weekday.type]
case class AnnotatedEnumHolder(@JsonScalaEnumeration(classOf[WeekdayType]) weekday: Weekday.Weekday)

@RunWith(classOf[JUnitRunner])
class EnumerationDeserializerTest extends FlatSpec with DeserializerTest with ShouldMatchers {

  lazy val module = DefaultScalaModule

	"An ObjectMapper with EnumDeserializerModule" should "deserialize a value into a scala Enumeration as a bean property" in {
		val expectedDay = Weekday.Fri
    val result = deserialize[EnumContainer](fridayEnumJson)
    result.day should be (expectedDay)
	}

  it should "deserialize an annotated Enumeration value" in {
    val result = deserialize[AnnotatedEnumHolder](annotatedFridayJson)
    result.weekday should be (Weekday.Fri)
  }

	val fridayEnumJson = """{"day": {"enumClass":"com.fasterxml.jackson.module.scala.Weekday","value":"Fri"}}"""

  val annotatedFridayJson = """{"weekday":"Fri"}"""
}