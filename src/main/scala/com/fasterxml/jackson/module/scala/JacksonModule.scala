package com.fasterxml.jackson.module.scala

import org.codehaus.jackson.Version
import org.codehaus.jackson.map.Module.SetupContext
import org.codehaus.jackson.map.{Deserializers, Serializers, Module}
import org.codehaus.jackson.map.`type`.TypeModifier

trait JacksonModule extends Module {

  private val serializers = Seq.newBuilder[Serializers]
  private val deserializers = Seq.newBuilder[Deserializers]
  private val typeModifiers = Seq.newBuilder[TypeModifier]

  def getModuleName = "JacksonModule"

  // TODO: Keep in sync with POM
  val version = new Version(0,5,0,"SNAPSHOT")

  def setupModule(context: SetupContext)
  {
    serializers.result().foreach(context.addSerializers(_))
    deserializers.result().foreach(context.addDeserializers(_))
    typeModifiers.result().foreach(context.addTypeModifier(_))
  }

  protected def +=(ser: Serializers) = serializers += ser
  protected def +=(deser: Deserializers) = deserializers += deser
  protected def +=(typeMod: TypeModifier) = typeModifiers += typeMod
}