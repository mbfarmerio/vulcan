/*
 * Copyright 2019 OVO Energy Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package object vulcan {

  /**
    * Encodes the specified value using the [[Codec]]
    * for the value's type.
    *
    * {{{
    * scala> encode(10)
    * res0: Either[AvroError, Any] = Right(10)
    * }}}
    */
  final def encode[A](a: A)(implicit codec: Codec[A]): Either[AvroError, Any] =
    codec.schema.flatMap(codec.encode(a, _))

  /**
    * Decodes the specified value using the [[Codec]]
    * for the specified type.
    *
    * {{{
    * scala> decode[Int](10)
    * res0: Either[AvroError, Int] = Right(10)
    * }}}
    */
  final def decode[A](value: Any)(implicit codec: Codec[A]): Either[AvroError, A] =
    codec.schema.flatMap(codec.decode(value, _))
}
