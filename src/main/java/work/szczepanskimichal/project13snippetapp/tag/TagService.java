package work.szczepanskimichal.project13snippetapp.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag findByName(String name) {
        return tagRepository.findTagByTagName(name);
    }

}
