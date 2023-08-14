select
    a.ATTACHED_DOCUMENT_ID as ATTACHMENT_ID,
    d.DOCUMENT_ID,
    c.USER_NAME as CATEGORY,
    d.FILE_NAME,
    b.FILE_NAME as BASE_FILE_NAME,
    b.FILE_CONTENT_TYPE as CONTENT_TYPE,
    --xa.THUMBNAIL,
    nvl(xa.THUMBNAIL,
        DECODE(
            b.FILE_CONTENT_TYPE,
            'image/jpg', b.FILE_DATA,
            'image/jpeg', b.FILE_DATA,
            'image/pjpeg', b.FILE_DATA,
            null
        )
    ) as THUMBNAIL,
    null as DATA,
    dbms_lob.getlength(b.FILE_DATA) as DATA_SIZE,
    a.CREATION_DATE,
    a.CREATED_BY,
    a.LAST_UPDATE_DATE,
    a.LAST_UPDATED_BY,
    null as REMOVE_DATE,
    null as REMOVED_BY
from apps.fnd_attached_documents a
left join apps.xxapps_thumbnails xa on xa.ATTACHED_DOCUMENT_ID = a.ATTACHED_DOCUMENT_ID
left join apps.fnd_documents d on d.DOCUMENT_ID = a.DOCUMENT_ID
left join apps.fnd_document_categories_tl c on c.CATEGORY_ID = d.CATEGORY_ID and c.LANGUAGE = SYS_CONTEXT('USERENV','LANG')
left join apps.fnd_lobs b on b.FILE_ID = d.MEDIA_ID
where a.ENTITY_NAME = :p_entity_name
    and a.PK1_VALUE = :p_pk1 --19915
    and ((a.PK2_VALUE is null) or (a.PK2_VALUE = :p_pk2))
    and ((a.PK3_VALUE is null) or (a.PK3_VALUE = :p_pk3))
