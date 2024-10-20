package org.example.API.model;


import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ASTService {
    @Autowired
    private ASTRepository astRepository;

    private static final Logger logger = LoggerFactory.getLogger(ASTService.class);

    public ASTENTITY saveAST(ASTENTITY ast) {
        return astRepository.save(ast);
    }

    public ASTENTITY getEntityById(ObjectId id) {

        logger.info("Fetching entity with id: {}", id);


        ASTENTITY ast = astRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        return ast;
    }

    // Other service methods
}

