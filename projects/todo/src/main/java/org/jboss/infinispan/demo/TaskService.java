package org.jboss.infinispan.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.cache.annotation.CacheRemoveAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infinispan.Cache;
import org.jboss.infinispan.demo.model.Task;

@Named
@ApplicationScoped
public class TaskService {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	Cache<Long, Task> cache;

	public List<Task> findAll() {
		return new ArrayList<Task>(cache.values());
	}

	public void create(Task task) {
		Long id = new Long(cache.size() + 1);
		task.setId(id);
		task.setCreatedOn(new Date());
		cache.put(id, task);
	}

	public Task find(Long id) {
		return cache.get(id);
	}

	public void update(Task task) {
		cache.replace(task.getId(), task);
	}

	public void delete(Long id) {
		cache.remove(id);
	}

	@CacheRemoveAll()
	public void clearCache() {
	}

	@PreDestroy
	public void destory() {
		if (cache != null) {
			cache.stop();
			cache.getCacheManager().stop();
		}
	}

}
