package org.sanchain.core.types.known.tx.result;

import org.sanchain.core.types.known.sle.LedgerEntry;
import org.sanchain.core.uint.UInt32;
import org.sanchain.core.STArray;
import org.sanchain.core.STObject;
import org.sanchain.core.serialized.enums.EngineResult;
import org.sanchain.core.uint.UInt8;

import java.util.Iterator;

public class TransactionMeta extends STObject {
    public static boolean isTransactionMeta(STObject source) {
        return source.has(UInt8.TransactionResult) &&
                source.has(STArray.AffectedNodes);
    }

    public EngineResult engineResult() {
        return engineResult(this);
    }

    public Iterable<AffectedNode> affectedNodes() {
        STArray nodes = get(STArray.AffectedNodes);
        final Iterator<STObject> iterator = nodes.iterator();
        return new Iterable<AffectedNode>() {
            @Override
            public Iterator<AffectedNode> iterator() {
                return iterateAffectedNodes(iterator);
            }
        };
    }

    public void walkPrevious(LedgerEntry.OnLedgerEntry cb) {
        for (AffectedNode affectedNode : affectedNodes()) {
            if (affectedNode.wasPreviousNode()) {
                cb.onObject(affectedNode.nodeAsPrevious());
            }
        }
    }
    public static Iterator<AffectedNode> iterateAffectedNodes(final Iterator<STObject> iterator) {
        return new Iterator<AffectedNode>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public AffectedNode next() {
                return (AffectedNode) iterator.next();
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    public UInt32 transactionIndex() {
        return get(UInt32.TransactionIndex);
    }
}
