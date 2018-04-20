package org.sanchain.core.types.known.tx.txns;


import org.sanchain.client.api.model.AmountObj;
import org.sanchain.client.api.model.TxObj;
import org.sanchain.core.Amount;
import org.sanchain.core.fields.Field;
import org.sanchain.core.serialized.enums.TransactionType;
import org.sanchain.core.types.known.tx.Transaction;
import org.sanchain.core.uint.UInt32;

public class TrustSet extends Transaction {
    public TrustSet() {
        super(TransactionType.TrustSet);
    }

    public UInt32 qualityIn() {return get(UInt32.QualityIn);}
    public UInt32 qualityOut() {return get(UInt32.QualityOut);}
    public Amount limitAmount() {return get(Amount.LimitAmount);}
    public void qualityIn(UInt32 val) {put(Field.QualityIn, val);}
    public void qualityOut(UInt32 val) {put(Field.QualityOut, val);}
    public void limitAmount(Amount val) {put(Field.LimitAmount, val);}
    @Override
    public TxObj analyze(String address){
        init();
        Amount limit = limitAmount();
        AmountObj limitAmount = new AmountObj(limit.valueText(), limit.currencyString(), limit.issuerString());
        item.setLimitAmount(limitAmount);
        item.setRecipient(limit.issuer().address);
        if (!limit.issuer().address.equals(address))
            item.setContact(limit.issuer().address);
        item.setType(limit.issuer().address.equals(address) ? "connected" : "connecting");
        return item;
    }
}
