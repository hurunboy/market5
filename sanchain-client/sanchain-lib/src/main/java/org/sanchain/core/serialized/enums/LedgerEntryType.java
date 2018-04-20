package org.sanchain.core.serialized.enums;

import org.sanchain.core.serialized.BinaryParser;
import org.sanchain.core.serialized.BytesSink;
import org.sanchain.core.serialized.SerializedType;
import org.sanchain.core.serialized.TypeTranslator;
import org.ripple.bouncycastle.util.encoders.Hex;

import java.util.TreeMap;

public enum LedgerEntryType implements SerializedType{
    Invalid (-1),
    AccountRoot ('a'),
    DirectoryNode('d'),
    GeneratorMap ('g'),
    RippleState ('r'),
    // Nickname ('n'), // deprecated
    Offer ('o'),
    Contract ('c'),
    LedgerHashes ('h'),
    EnabledAmendments('f'),
    FeeSettings ('s'),
    Ticket('T'),
    Dividend('D'),
    Refer('R'),
    Asset('t'),
    AssetState('S');

    final int ord;
    LedgerEntryType(int i) {
        ord = i;
    }

    static private TreeMap<Integer, LedgerEntryType> byCode = new TreeMap<Integer, LedgerEntryType>();
    static {
        for (Object a : LedgerEntryType.values()) {
            LedgerEntryType f = (LedgerEntryType) a;
            byCode.put(f.ord, f);
        }
    }

    public static LedgerEntryType fromNumber(Number i) {
        return byCode.get(i.intValue());
    }

    public Integer asInteger() {
        return ord;
    }

    // SeralizedType interface
    @Override
    public byte[] toBytes() {
        return new byte[]{(byte) (ord >> 8), (byte) (ord & 0xFF)};
    }
    @Override
    public Object toJSON() {
        return toString();
    }
    @Override
    public String toHex() {
        return Hex.toHexString(toBytes());
    }
    @Override
    public void toBytesSink(BytesSink to) {
        to.add(toBytes());
    }
    public static class Translator extends TypeTranslator<LedgerEntryType> {
        @Override
        public LedgerEntryType fromParser(BinaryParser parser, Integer hint) {
            byte[] read = parser.read(2);
            return fromNumber((read[0] << 8) | read[1]);
        }

        @Override
        public LedgerEntryType fromInteger(int integer) {
            return fromNumber(integer);
        }

        @Override
        public LedgerEntryType fromString(String value) {
            return LedgerEntryType.valueOf(value);
        }
    }
    public static Translator translate = new Translator();
}
