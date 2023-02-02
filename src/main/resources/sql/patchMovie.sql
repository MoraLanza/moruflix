UPDATE movies SET
    title = CASE WHEN :TITLE::varchar IS NULL THEN title ELSE :TITLE END,
    resume = CASE WHEN :RESUME::varchar IS NULL THEN resume ELSE :RESUME END,
    popularity = CASE WHEN :POPULARITY::double IS NULL THEN popularity ELSE :POPULARITY END,
    runtime = CASE WHEN :RUNTIME::integer IS NULL THEN runtime ELSE :RUNTIME END,
     release_date = CASE WHEN :RELEASE_DATE::date IS NULL THEN release_date ELSE :RELEASE_DATE END
WHERE movie_id = :MOVIE_ID
RETURNING *