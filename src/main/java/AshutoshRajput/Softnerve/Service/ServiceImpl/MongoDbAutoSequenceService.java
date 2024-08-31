package AshutoshRajput.Softnerve.Service.ServiceImpl;

import AshutoshRajput.Softnerve.Entity.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@Service
public class MongoDbAutoSequenceService {
    @Autowired
    private MongoOperations mongoOperations;

    public Long getsequencenumber(String sequencename){
        Query query=new Query(Criteria.where("id").is(sequencename));
        Update update=new Update().inc("seq",1);
        DbSequence sequence=mongoOperations
                .findAndModify(query,update,options().returnNew(true).upsert(true),
                        DbSequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq():1;

    }
}
