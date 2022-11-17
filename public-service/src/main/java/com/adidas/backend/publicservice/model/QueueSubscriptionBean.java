/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.adidas.backend.publicservice.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class QueueSubscriptionBean extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"QueueSubscriptionBean\",\"namespace\":\"com.adidas.backend.publicservice.model\",\"fields\":[{\"name\":\"email\",\"type\":\"string\"}]}");
  private static final long serialVersionUID = -2570186968617027150L;
  private static final SpecificData MODEL$ = new SpecificData();
  private static final BinaryMessageEncoder<QueueSubscriptionBean> ENCODER =
          new BinaryMessageEncoder<QueueSubscriptionBean>(MODEL$, SCHEMA$);
  private static final BinaryMessageDecoder<QueueSubscriptionBean> DECODER =
          new BinaryMessageDecoder<QueueSubscriptionBean>(MODEL$, SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<QueueSubscriptionBean>
          WRITER$ = (org.apache.avro.io.DatumWriter<QueueSubscriptionBean>) MODEL$.createDatumWriter(SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<QueueSubscriptionBean>
          READER$ = (org.apache.avro.io.DatumReader<QueueSubscriptionBean>) MODEL$.createDatumReader(SCHEMA$);
  private java.lang.CharSequence email;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public QueueSubscriptionBean() {
  }

  /**
   * All-args constructor.
   * @param email The new value for email
   */
  public QueueSubscriptionBean(java.lang.CharSequence email) {
    this.email = email;
  }

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<QueueSubscriptionBean> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<QueueSubscriptionBean> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<QueueSubscriptionBean> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<QueueSubscriptionBean>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Deserializes a QueueSubscriptionBean from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a QueueSubscriptionBean instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static QueueSubscriptionBean fromByteBuffer(
          java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /**
   * Creates a new QueueSubscriptionBean RecordBuilder.
   * @return A new QueueSubscriptionBean RecordBuilder
   */
  public static com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder newBuilder() {
    return new com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder();
  }

  /**
   * Creates a new QueueSubscriptionBean RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new QueueSubscriptionBean RecordBuilder
   */
  public static com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder newBuilder(com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder other) {
    if (other == null) {
      return new com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder();
    } else {
      return new com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder(other);
    }
  }

  /**
   * Creates a new QueueSubscriptionBean RecordBuilder by copying an existing QueueSubscriptionBean instance.
   * @param other The existing instance to copy.
   * @return A new QueueSubscriptionBean RecordBuilder
   */
  public static com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder newBuilder(com.adidas.backend.publicservice.model.QueueSubscriptionBean other) {
    if (other == null) {
      return new com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder();
    } else {
      return new com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder(other);
    }
  }

  /**
   * Serializes this QueueSubscriptionBean to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  public org.apache.avro.specific.SpecificData getSpecificData() {
    return MODEL$;
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }

  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0:
        return email;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0:
        email = (java.lang.CharSequence) value$;
        break;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'email' field.
   * @return The value of the 'email' field.
   */
  public java.lang.CharSequence getEmail() {
    return email;
  }

  /**
   * Sets the value of the 'email' field.
   * @param value the value to set.
   */
  public void setEmail(java.lang.CharSequence value) {
    this.email = value;
  }

  @Override
  public void writeExternal(java.io.ObjectOutput out)
          throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @Override
  public void readExternal(java.io.ObjectInput in)
          throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override
  protected boolean hasCustomCoders() {
    return true;
  }

  @Override
  public void customEncode(org.apache.avro.io.Encoder out)
          throws java.io.IOException {
    out.writeString(this.email);

  }

  @Override
  public void customDecode(org.apache.avro.io.ResolvingDecoder in)
          throws java.io.IOException {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.email = in.readString(this.email instanceof Utf8 ? (Utf8) this.email : null);

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
          case 0:
            this.email = in.readString(this.email instanceof Utf8 ? (Utf8) this.email : null);
            break;

          default:
            throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }

  /**
   * RecordBuilder for QueueSubscriptionBean instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<QueueSubscriptionBean>
          implements org.apache.avro.data.RecordBuilder<QueueSubscriptionBean> {

    private java.lang.CharSequence email;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.email)) {
        this.email = data().deepCopy(fields()[0].schema(), other.email);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing QueueSubscriptionBean instance
     * @param other The existing instance to copy.
     */
    private Builder(com.adidas.backend.publicservice.model.QueueSubscriptionBean other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.email)) {
        this.email = data().deepCopy(fields()[0].schema(), other.email);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Gets the value of the 'email' field.
     * @return The value.
     */
    public java.lang.CharSequence getEmail() {
      return email;
    }


    /**
     * Sets the value of the 'email' field.
     * @param value The value of 'email'.
     * @return This builder.
     */
    public com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder setEmail(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.email = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'email' field has been set.
     * @return True if the 'email' field has been set, false otherwise.
     */
    public boolean hasEmail() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'email' field.
     * @return This builder.
     */
    public com.adidas.backend.publicservice.model.QueueSubscriptionBean.Builder clearEmail() {
      email = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public QueueSubscriptionBean build() {
      try {
        QueueSubscriptionBean record = new QueueSubscriptionBean();
        record.email = fieldSetFlags()[0] ? this.email : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}









