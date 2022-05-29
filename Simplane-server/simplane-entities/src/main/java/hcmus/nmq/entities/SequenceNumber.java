package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 5:29 PM 5/29/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "sequence_number")
public class SequenceNumber {
    @Id
    @Field("SEQ_NAME")
    private String seqName;

    @Field("SEQ_ID")
    private Integer seqId;

    @Field("LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Field("LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Field("CREATED_STAMP")
    private Date createdStamp;

    @Field("CREATED_TX_STAMP")
    private Date createdTxStamp;
}