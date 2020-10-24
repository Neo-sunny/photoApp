DROP TABLE IF EXISTS images;

CREATE TABLE images (
  id LONG AUTO_INCREMENT  PRIMARY KEY,
  img_id LONG NOT NULL,
  img_url VARCHAR(5000) NOT NULL
  );
insert into images
values(100l,2,'https://loremflickr.com/cache/resized/65535_49893876713_6f84caeef0_320_240_nofilter.jpg');
