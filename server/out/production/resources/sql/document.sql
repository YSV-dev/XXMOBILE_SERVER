select
    d.DOCUMENT_ID       as DOCUMENT_ID,
    b.FILE_CONTENT_TYPE as CONTENT_TYPE,
    b.FILE_DATA         as DATA,
    d.URL               as URL
from apps.fnd_documents d
left join apps.fnd_lobs b on b.FILE_ID = d.MEDIA_ID
where d.DOCUMENT_ID = :p_document_id