package com.fasterxml.jackson.module.scala

import com.fasterxml.jackson.databind.{Module, ObjectMapper}

trait JacksonTest {

  def module: Module

  def mapper = {
    val result = new ObjectMapper
    result.registerModule(module)
    result
  }

}