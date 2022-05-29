package hcmus.nmq.simplaneservice.repositories.impls;

import hcmus.nmq.entities.SequenceNumber;
import hcmus.nmq.simplaneservice.repositories.ISequenceNumberRepositoryCustom;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 5:34 PM 5/29/2022
 * LeHongQuan
 */

public class ISequenceNumberRepositoryImpl extends BaseRepositoryCustom implements ISequenceNumberRepositoryCustom {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized String getSequence(Class forClass) {
        String sequenceName = forClass.getName();
        SequenceNumber sequenceNumber = sequenceNumberRepository.findBySeqName(sequenceName);
        if (null == sequenceNumber) {
            sequenceNumber = new SequenceNumber();
            sequenceNumber.setSeqName(sequenceName);
            sequenceNumber.setSeqId(1000);

            sequenceNumber.setLastUpdatedStamp(new Date());
            sequenceNumber.setCreatedStamp(new Date());
            sequenceNumberRepository.save(sequenceNumber);
            return "1000";
        }
        int sequenceId = sequenceNumber.getSeqId() + 1;
        sequenceNumber.setSeqId(sequenceId);
        sequenceNumber.setLastUpdatedStamp(new Date());
        sequenceNumberRepository.save(sequenceNumber);
        return String.valueOf(sequenceId);
    }
}