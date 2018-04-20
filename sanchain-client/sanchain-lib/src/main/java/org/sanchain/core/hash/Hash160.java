package org.sanchain.core.hash;

import org.sanchain.core.AccountID;
import org.sanchain.core.fields.Field;
import org.sanchain.core.fields.TypedFields;
import org.sanchain.core.serialized.BytesSink;

public class Hash160 extends Hash<Hash160> {
    public Hash160(byte[] bytes) {
        super(bytes, 20);
    }

    @Override
    public Object toJSON() {
        return translate.toJSON(this);
    }

    @Override
    public byte[] toBytes() {
        return translate.toBytes(this);
    }

    @Override
    public String toHex() {
        return translate.toHex(this);
    }

    @Override
    public void toBytesSink(BytesSink to) {
        translate.toBytesSink(this, to);
    }

    public static class Translator extends HashTranslator<Hash160> {
        @Override
        public Hash160 newInstance(byte[] b) {
            return new Hash160(b);
        }

        @Override
        public int byteWidth() {
            return 20;
        }

        @Override
        public Hash160 fromString(String value) {
            if (value.startsWith("r")) {
                return newInstance(AccountID.fromAddress(value).bytes());
            }
            return super.fromString(value);
        }
    }
    public static Translator translate = new Translator();

    public static TypedFields.Hash160Field hash160Field(final Field f) {
        return new TypedFields.Hash160Field(){ @Override public Field getField() {return f;}};
    }

    static public TypedFields.Hash160Field TakerPaysIssuer = hash160Field(Field.TakerPaysIssuer);
    static public TypedFields.Hash160Field TakerGetsCurrency = hash160Field(Field.TakerGetsCurrency);
    static public TypedFields.Hash160Field TakerPaysCurrency = hash160Field(Field.TakerPaysCurrency);
    static public TypedFields.Hash160Field TakerGetsIssuer = hash160Field(Field.TakerGetsIssuer);
}
