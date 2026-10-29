package dio.budgeting.application;

import dio.budgeting.application.output.TransactionOutput;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionsByCategoryUseCase {
    private final TransactionRepository repository;

    public ListTransactionsByCategoryUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<TransactionOutput> execute(Category category) {
        return repository.findAllByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
